FROM python:3.11-slim

WORKDIR /app

RUN apt-get update && apt-get install -y \
    python3-tk \
    x11-apps \
    && rm -rf /var/lib/apt/lists/*

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY calculator.py .
COPY calculator_gui.py .
COPY test_calculator.py .

ENV DISPLAY=:0

CMD ["python", "calculator_gui.py"]
