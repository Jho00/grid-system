import { CONFIG } from './net-config';
import net from 'net';


const client = new net.Socket();

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

client.on('close', () => { 
   client.write(JSON.stringify(
    {
      action: 'disconnect',
      payload: {
        address: CONFIG.CLIENT.address,
        port: CONFIG.CLIENT.port,
      }
    }
   ));
});
