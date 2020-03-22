import { CONFIG } from './net-config';
import net from 'net';

const client = new net.Socket();

runClient();

const server = net.createServer((socket) => {
  socket.on('data', function(data){
    const command = JSON.parse(data.toString());
    if (command.action == 'task') {
      const initialData = command.payload.initialData;
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


function runClient() {
  console.log('try to connect');
  client.connect(CONFIG.PEER.port, CONFIG.PEER.address, () => {
    console.log('connected');
    client.write(JSON.stringify(
      {
        action: 'connect',
        payload: {
          address: CONFIG.CLIENT.address,
          port: CONFIG.CLIENT.port,
        }
      }
    ));
  });
}