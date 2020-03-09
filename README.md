# grid-system

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
