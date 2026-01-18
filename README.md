🧩 Netflix Conductor – Local Setup (POC)

This repo contains a local Netflix Conductor setup used to orchestrate my Java microservices 🚀
Built from source and run via Docker, without breaking my Java 21 services.

🛠 What you need

☕ Java 21 → for microservices

☕ Java 17 → only to build Conductor

🐳 Docker Desktop

🌱 Git

Don’t worry — Java 17 is temporary and won’t mess with your setup.

▶️ Build & Run Conductor (Windows)
1️⃣ Temporarily switch to Java 17
$env:JAVA_HOME="C:\Program Files\Java\jdk-17.0.2"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"
java -version

2️⃣ Build Conductor
cd conductor
./gradlew clean build -x test

3️⃣ Start Conductor with Docker 🐳
cd docker
docker compose -f docker-compose-lite.yml build
docker compose -f docker-compose-lite.yml up

✅ Verify it’s running

🖥 UI → http://localhost:8080

❤️ Health → http://localhost:8080/health

🧠 Redis starts automatically

If the UI loads, you’re good 😄

⏹ Stop / ▶️ Start again

Stop:

Ctrl + C

Start again:

docker compose -f docker-compose-lite.yml up

🧠 Good to know

❌ No ready-made Conductor Docker image anymore

✅ Build from source is the correct way

☕ Conductor builds on Java 17

🚀 Microservices stay on Java 21

🐳 Docker keeps everything clean & isolated

🎯 What’s done

✅ Conductor server running locally

✅ Redis connected

✅ UI & APIs verified

✅ Ready to plug in worker services

If you ever forget how this works — this README has your back 😄
Next up: workers, workflows, and real orchestration magic ✨
