# 🧠 Creating a Compute Module in Function Execution Mode on Palantir Foundry

This guide walks you through the step-by-step process of setting up a Compute Module using **Function Execution mode** in **Palantir Foundry** using a Python-based Docker container.

---

## 🚀 Steps to Create a Compute Module

### 1. Create a Docker Container with a Python Application
Structure your Python project and create a `Dockerfile` to containerize it.

Example:
```Dockerfile
FROM python:3.11-slim
WORKDIR /app
COPY . /app
RUN pip install -r requirements.txt
CMD ["foundry_compute_modules.entrypoint"]
```

---

### 2. Write Python Functions
Create simple Python functions that do calculations or transformations.

Example:
```python
from foundry_compute_modules import function

@function
def add_numbers(events):
    x = events["x"]
    y = events["y"]
    return {"sum": x + y}
```

---

### 3. Add the `@function` Annotation
Use the `@function` decorator from `foundry-compute-modules` above each callable function.

---

### 4. Create `requirements.txt`
Include at least the following dependency:

```
foundry-compute-modules
```

---

### 5. Store the Docker Container in Artifacts
Artifacts is Foundry’s internal Docker image registry.

---

### 6. Build and Push Image to Artifacts
- Go to **Artifacts**
- Click **Create Repository**
- In your Python app's root directory, run:

```bash
docker build --platform linux/amd64 -t mixite-container-registry.palantirfoundry.com/<image_name>:<tag> .
```

---

### 7. Push the Docker Image
Push your image using Docker:

```bash
docker push mixite-container-registry.palantirfoundry.com/<image_name>:<tag>
```

---

### 8. Configure the Compute Module
- Open the **Compute Modules** application in Foundry
- Click **Configure**
- Choose **Function Execution** mode

---

### 9. Create a Source Connection
- Go to the **Sources** tab
- Click **Add Source**
- Choose **Generic Connection**
- Give it a name and click **Create**

---

### 10. Create an Egress Policy
- Click on your new Source
- Go to **Egress Policy**
- Click **Create New**
- Paste the domain from your browser (e.g., `idt.usw-22.palantirfoundry.com`)
- Click **Next**, then **Request**, then **Self-Approve**
- (Optional) Enable exports for pipeline use
- Click **Save**

---

### 11. Add Your Docker Container
- Go to **Containers** tab
- Click **Add Container**
- Select your image from the Artifact repository
- Confirm and add the Foundry URL if prompted
- Your container should now show up

---

### 12. Start the Compute Module
- Go to the **Overview** tab
- Click **Start**
- The status should change to **Connected**

---

### 13. Verify Detected Functions
- Go to the **Functions** tab
- You should see functions from your cont
