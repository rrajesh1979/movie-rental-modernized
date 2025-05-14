FROM node:22-alpine AS frontend-builder

WORKDIR /app

# Copy package files
COPY package.json pnpm-lock.yaml pnpm-workspace.yaml ./

# Install pnpm and dependencies
RUN npm install -g pnpm && pnpm install --frozen-lockfile

# Copy all frontend files
COPY . .

# Build the Next.js app
RUN pnpm build

# Python stage
FROM python:3.11-slim

WORKDIR /app

# Copy the built Next.js app from the frontend-builder stage
COPY --from=frontend-builder /app/.next ./.next
COPY --from=frontend-builder /app/public ./public
COPY --from=frontend-builder /app/package.json ./package.json
COPY --from=frontend-builder /app/node_modules ./node_modules

# Copy API files
COPY api ./api
COPY next.config.ts ./

# Install Python dependencies
RUN pip install --no-cache-dir -r api/requirements.txt

# Copy necessary files for running
COPY scripts ./scripts

# Set environment variables
ENV NODE_ENV=production
ENV PORT=3000

# Expose ports
EXPOSE 3000
EXPOSE 8000

# Add startup script
COPY scripts/start.sh ./start.sh
RUN chmod +x ./start.sh

# Start both services
CMD ["./start.sh"] 