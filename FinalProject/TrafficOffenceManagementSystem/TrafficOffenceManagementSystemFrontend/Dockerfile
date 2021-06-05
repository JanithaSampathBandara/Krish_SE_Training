# Building Process

FROM node:10-alpine AS builder
WORKDIR /app
COPY package.json package-lock.json /app/
RUN npm install
COPY . /app
RUN npm run build

# Stage 2

FROM nginx:1.19.10-alpine
COPY --from=builder /app/dist/trafficoffencemanagementsystem /usr/share/nginx/html
EXPOSE 80