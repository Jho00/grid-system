var net = require('net');
var server = net.createServer(function(socket) {
  socket.write('Hello from server!');
  socket.on('data', function(data){
    console.log(JSON.parse(data.toString()).message);
  })
});

server.listen(8080,'127.0.0.1');