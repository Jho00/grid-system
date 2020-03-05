
var net = require('net');
var client = new net.Socket();
var os = require('os');
var ifaces = os.networkInterfaces();

client.connect(8080, '127.0.0.1', function() {
   console.log('connected to server!'); 
   console.log(getAddress());
   client.write(JSON.stringify({ message: 'Hello from client'}));
});

client.on('data', function(data) {
  console.log('Received: ' + data);
});
client.on('close', function() { 
   console.log('disconnected from server');
});


function getAddress() {
  Object.keys(ifaces).forEach(function (ifname) {
    var alias = 0;
  
    ifaces[ifname].forEach(function (iface) {
      if ('IPv4' !== iface.family || iface.internal !== false) {
        // skip over internal (i.e. 127.0.0.1) and non-ipv4 addresses
        return;
      }
  
      if (alias >= 1) {
        // this single interface has multiple ipv4 addresses
        console.log(ifname + ':' + alias, iface.address);
      } else {
        // this interface has only one ipv4 adress
        console.log(ifname, iface.address);
      }
      ++alias;
    });
  });
}