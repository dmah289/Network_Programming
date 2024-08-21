# TCP
## MultiThreadedServer
- Chạy main() để khởi tạo ServerSocket cho việc lắng nghe client
- Client và Server chạy trên cùng 1 máy → Cục bộ:
    - Cmd: `telnet localhost 2831`(IPv6) / `telnet 127.0.0.1 2831 (IPv4)`
- Client và Server chạy trên 2 máy cùng Wifi / Lan
    - `ipconfig`→ IPv4 Address
    - Cmd: `telnet [IPv4] 2831`