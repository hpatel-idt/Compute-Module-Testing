# 🧠 Creating a Compute Module in Function Execution Mode on Palantir Foundry
[Palantir](https://assets.streamlinehq.com/image/private/w_300,h_300,ar_1/f_auto/v1/icons/logos/palantir-xdp5vzpsdiuov68zwbgn.png/palantir-4vc1iawq4sqw5r2jh1n7cr.png?_a=DATAdtAAZAA0)
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
- You should see functions from your container listed

---

### 14. Import and Configure Function Inputs
- Click **Import** for each function
- Edit each function to add input parameters
- Example:
  - Input: `x` (float), `y` (float)
  - These must match the keys used inside your code’s `events` dict
- Set the output format
- Click **Update**

📝 **Note:** When calling these functions in Foundry, you must provide all required parameters inside the `events` dictionary.

---

### 15. Test Your Functions
- Go to **Overview → Query**
- Select a function
- Provide example input JSON
- Click **Run** to test

---

### 16. Register in Ontology Manager
- Go to **Ontology Manager → Functions**
- Search for your function name
- It can now be used anywhere in Foundry

---

## ✅ Example Function: Add Two Numbers

```python
@function
def add_numbers(events):
    x = float(events["x"])
    y = float(events["y"])
    return {"sum": x + y}
```

Input:
```json
{
  "x": 2.5,
  "y": 3.5
}
```

Output:
```json
{
  "sum": 6.0
}
```

---
