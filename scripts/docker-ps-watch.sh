# Watch Docker containers status
# Use Ctrl+C to stop
# Run with Windows Git Bash or Ubuntu terminal
# $ sh docker-ps-watch.sh

while true; do
  clear
  echo "Updated at: $(date '+%Y-%m-%d %H:%M:%S')"
  echo
  docker ps -a
  sleep 1
done
