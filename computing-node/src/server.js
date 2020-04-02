import { CONFIG } from './net-config';
import net from 'net';

const client = new net.Socket();

runClient();

const server = net.createServer((socket) => {
  socket.on('data', function(data){
    console.log(data);
    let dataStr = data.toString();
    const newStrt = dataStr.substring(0, dataStr.length);

    console.log(newStrt);
    const command = JSON.parse(newStrt);
    console.log(command);

    if (command.action == 'task') {
      const initialData = JSON.parse(command.payload.data);
      const result = eval(command.payload.toExecute);
      const resultJson = JSON.stringify(
        {
          status: 'ok',
          result: JSON.stringify(result),
        }
      );
      console.log(resultJson);
      socket.write(resultJson + "\n");
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
    const command = JSON.stringify(
      {
        action: 'connect',
        payload: {
          address: CONFIG.CLIENT.address,
          port: CONFIG.CLIENT.port,
        }
      }
    );
    console.log(command);
    client.write(command + "\n");

  });
}