var net = require('net');
var server = net.createServer(function(socket) {
  socket.on('data', function(data){
    var command = JSON.parse(data.toString());

    if (command.action == 'Execute') {
    	eval(command.payload.toExecute);
    }
    socket.write('Hello from server!');	
    console.log('sent');
  })

  socket.on('error', function(err) {
  	console.log(err);
  	// if client abruptly stop connection 
  })
});

console.log('CREATED');
server.listen(8080,'127.0.0.1');
