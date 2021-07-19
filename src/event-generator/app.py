from confluent_kafka import Producer, KafkaError
import json
import time

if __name__ == '__main__':

    # Read arguments and configurations and initialize
    topic = "events"
    conf = {
        'bootstrap.servers': "broker:29092"
        }
    producer = Producer(conf)


    delivered_records = 0

    # Optional per-message on_delivery handler (triggered by poll() or flush())
    # when a message has been successfully delivered or
    # permanently failed delivery (after retries).
    def acked(err, msg):
        global delivered_records
        """Delivery report handler called on
        successful or failed delivery of message
        """
        if err is not None:
            print("Failed to deliver message: {}".format(err))
        else:
            delivered_records += 1
            print("Produced record to topic {} partition [{}] @ offset {}"
                  .format(msg.topic(), msg.partition(), msg.offset()))

    n=0
    while True:
        n=n+1
        record_value = json.dumps({'count': n})
        print("Producing record: {}\t{}".format(n, record_value))
        producer.produce(topic, key=str(n), value=record_value, on_delivery=acked)
        producer.poll(0)
        time.sleep(1)

    producer.flush()


