import requests
from compute_modules.annotations import function

@function
def add(context, event):
    return str(event['x'] + event['y'])

@function
def hello(context, event):
    return 'Hello' + event['name']

@function
def calculate(context, event):
    args = {
        "timestamp": event['timestamp'],
        "idNumber": event['idNumber'],
        "x": event['x'],
        "y": event['y'],
        "z": event['z'],
        "covariance": event['covariance']
    }
    response = requests.post("http://localhost:8080/calculate", json=args)
    if "error" in response.text.lower() or "invalid" in response.text.lower():
        return {
            "timestamp": event['timestamp'],
            "idNumber": event['idNumber'],
            "message": "Request Failed"
        }
    else:
        return response.json()