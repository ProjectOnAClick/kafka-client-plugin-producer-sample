![image](https://github.com/user-attachments/assets/08c3f17f-376a-407b-b5f8-2fcb10918f0a)

The annotation itself is just metadata on a method, but the plugin you built turns that metadata into a live Kafka consumer at runtime.

startConsumers() → scans for @KafkaHandler

Who	                                         What happens
KafkaConsumerContainer.start()	            Calls ReflectionUtils.findHandlers(basePackage)
ReflectionUtils                             Uses Reflections library to find every method with @KafkaHandler inside com.myapp.handlers.
