# Creating a Compute Module in Function Execution Mode on Palantir Foundry

This guide walks you through the step-by-step process of setting up a Compute Module using **Function Execution mode** in **Palantir Foundry** using a Python-based Docker container.

---

## Steps to Create a Compute Module

### 1. Create a Docker Container with a Python Application
Structure your Python project and create a `Dockerfile` to containerize it.

Example:
```Dockerfile
FROM python:3.11-slim
WORKDIR /app
COPY . /app
RUN pip install -r requirements.txt
CMD ["foundry_compute_modules.entrypoint"]```

### 1. Create a Docker Container with a Python Application
Structure your Python project and create a `Dockerfile` to containerize it.

Example:
```Dockerfile
FROM python:3.11-slim
WORKDIR /app
COPY . /app
RUN pip install -r requirements.txt
CMD ["foundry_compute_modules.entrypoint"]```

### 1. Create a Docker Container with a Python Application
Structure your Python project and create a `Dockerfile` to containerize it.

Example:
```Dockerfile
FROM python:3.11-slim
WORKDIR /app
COPY . /app
RUN pip install -r requirements.txt
CMD ["foundry_compute_modules.entrypoint"]```
