import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private stompClient: Stomp.Client;

  constructor() {
  }

  ngOnInit(): void {
    this.connectServerSocket();
  }

  private connectServerSocket(): void {
    const ws = new SockJS('http://localhost:8081/socket');
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = (debug: any) => {
      console.log(debug);
    };

    const that = this;
    this.stompClient.connect({}, (connect: any) => {
      console.log(connect);
      that.stompClient.subscribe(`testando`, (result: any) => {
        console.log(result);
      });
    });
  }
}
