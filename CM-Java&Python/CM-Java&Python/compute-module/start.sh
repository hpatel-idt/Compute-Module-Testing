#!/bin/bash
# Start the Java server in the background. '&' starts the Java server in the background, so it doesn't block the shell
java -jar app.jar &

# Wait for Java server to start
sleep 10

# Run module
python3 python/compute-module.py
