#!/bin/sh

. ./image-env.sh

EVENT_SERVICE_URL=http://localhost:8081

podman run -i --rm -p 8080:8080 -e EVENT_SERVICE_URL=${EVENT_SERVICE_URL} ${PROJECT_ID}-${ARTIFACT_ID}:${GIT_HASH}