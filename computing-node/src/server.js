import { CONFIG } from './net-config';
import net from 'net';


const server = net.createServer((socket) => {
  socket.on('data', function(data){
    const command = JSON.parse(data.toString());

    if (command.action == 'task') {
      const result = eval(command.payload.toExecute);
      
      socket.write(JSON.stringify(
        {
          status: 'ok',
          result: result.toString(),
        }
      ));
    }
  })

  socket.on('error', (err) => {
  	console.log(err);
  })
});

server.listen(CONFIG.CLIENT.port, CONFIG.CLIENT.address);
