#!/bin/bash

# Start the FastAPI server in the background
cd /app && python -m uvicorn api.main:app --host 0.0.0.0 --port 8000 &

# Start the Next.js server
cd /app && npm start 