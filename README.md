# File Ip Counter
The program is designed to determine the number of unique IPv4 addresses in a file. Each line corresponds to one address, a file looks like this:

```
145.67.23.4
8.34.5.23
89.54.3.124
89.54.3.124
3.45.71.5
...
```

A file size is not limited and can take tens and hundreds of gigabytes.

For the application to work correctly, it is necessary to __pass the path__ of the file being read as the first command line argument.