#!/bin/bash

set -eo pipefail

echo "Executing tasks: [monkey runner]"


adb shell "monkey -p com.demo.movies -v 1000"