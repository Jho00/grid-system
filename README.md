# grid-system

COMPUTING NODE

```
1. cd computing-node
2. npm run build
3. npm run start
```

CONTRACTS

```
{
  "action": "connect",
  "payload": {
    "address": <string>, 
    "port": <number>
  }
}


{
  "action": "task",
  "payload": {
    "toExecute": <string>
  }
}
  
  
{
  "action": "disconnect",
  "payload": {
    "address": <string>, 
    "port": <number>
  }
}
```


RESPONSES

```
{
  "status": <"ok" | "fail">,
  "result": <string>
}
```
