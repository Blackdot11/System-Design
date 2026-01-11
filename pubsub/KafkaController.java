import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaController {

    private final Map<String, Topic> topics = new ConcurrentHashMap<>();
    private final Map<String, List<TopicSubscriber>> topicSubscribers = new ConcurrentHashMap<>();
    private final ExecutorService subscriberExecutor = Executors.newCachedThreadPool();
    private final AtomicInteger topicIdCounter = new AtomicInteger(0);

    public Topic createTopic(String topicName) {
        String topicId = String.valueOf(topicIdCounter.incrementAndGet());
        Topic topic = new Topic(topicName, topicId);
        topics.put(topicId, topic);
        topicSubscribers.put(topicId, new CopyOnWriteArrayList<>());
        System.out.println("Created topic: " + topicName + " with id: " + topicId);
        return topic;
    }

    public void subscribe(ISubscriber subscriber, String topicId) {
        Topic topic = topics.get(topicId);
        if (topic == null) {
            System.err.println("Topic with id " + topicId + " does not exist");
            return;
        }

        TopicSubscriber ts = new TopicSubscriber(topic, subscriber);
        topicSubscribers.get(topicId).add(ts);
        subscriberExecutor.submit(new TopicSubscriberController(ts));

        System.out.println(
            "Subscriber " + subscriber.getId() + " subscribed to topic: " + topic.getTopicName()
        );
    }

    public void publish(IPublisher publisher, String topicId, Message message) {
        Topic topic = topics.get(topicId);
        if (topic == null) {
            throw new IllegalArgumentException("Topic with id " + topicId + " does not exist");
        }

        topic.addMessage(message);

        for (TopicSubscriber ts : topicSubscribers.get(topicId)) {
            synchronized (ts) {
                ts.notify();
            }
        }

        System.out.println(
            "Message \"" + message.getMessage() + "\" published to topic: " + topic.getTopicName()
        );
    }

    public void resetOffset(String topicId, ISubscriber subscriber, int newOffset) {
        List<TopicSubscriber> subscribers = topicSubscribers.get(topicId);
        if (subscribers == null) return;

        for (TopicSubscriber ts : subscribers) {
            if (ts.getSubscriber().getId().equals(subscriber.getId())) {
                ts.getOffset().set(newOffset);
                synchronized (ts) {
                    ts.notify();
                }
                break;
            }
        }
    }

    public void shutdown() {
        subscriberExecutor.shutdownNow();
    }
}
