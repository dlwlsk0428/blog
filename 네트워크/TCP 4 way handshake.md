### 4 way handshake - 연결 해제

연결 성립 후, 모든 통신이 끝났다면 해제해야 한다.

<img src="https://media.geeksforgeeks.org/wp-content/uploads/CN.png">

1) 클라이언트는 서버에게 연결을 종료한다는 FIN 플래그를 보낸다.

2) 서버는 FIN을 받고, 확인했다는 ACK를 클라이언트에게 보낸다. (이때 모든 데이터를 보내기 위해 CLOSE_WAIT 상태가 된다)

3) 데이터를 모두 보냈다면, 연결이 종료되었다는 FIN 플래그를 클라이언트에게 보낸다.

4) 클라이언트는 FIN을 받고, 확인했다는 ACK를 서버에게 보낸다. (아직 서버로부터 받지 못한 데이터가 있을 수 있으므로 TIME_WAIT을 통해 기다린다.)

- 서버는 ACK를 받은 이후 소켓을 닫는다 (Closed)

- TIME_WAIT 시간이 끝나면 클라이언트도 닫는다 (Closed)

<br>

이렇게 4번의 통신이 완료되면 연결이 해제된다.

<br>
