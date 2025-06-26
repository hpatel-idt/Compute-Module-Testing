FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml ./
COPY src ./src

# Build fat jar
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the fat jar from the build stage
COPY --from=build /app/target/compute-module-1.0.0.jar ./app.jar

# Install Python, pip, and venv
RUN apt-get update && \
    apt-get install -y python3 python3-pip python3-venv && \
    rm -rf /var/lib/apt/lists/*

# Copy python code and requirements.txt
COPY python/ ./python/
COPY requirements.txt ./

# Install python dependencies
# Create virtual environment
RUN python3 -m venv /opt/venv && \
    /opt/venv/bin/pip install --upgrade pip && \
    /opt/venv/bin/pip install --no-cache-dir -r requirements.txt

# Add venv to PATH
ENV PATH="/opt/venv/bin:$PATH"

# Copy start script
COPY start.sh ./

# Expose port your Java server listens on
EXPOSE 8080
RUN chmod +x start.sh

USER 5001

ENTRYPOINT ["./start.sh"]