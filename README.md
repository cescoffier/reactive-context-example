
```bash
mvn compile vertx:run
```

In 2 different terminals, launch:

(require HTTPIE)

```bash
for i in {0..100}; do 
    http :8080 name==bar-${i}
done
```