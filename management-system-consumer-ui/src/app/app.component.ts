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

  matchList: Match[] = [];

  constructor() {
  }

  ngOnInit(): void {
    this.connectServerSocket();
  }

  private connectServerSocket(): void {
    const ws = new SockJS('http://localhost:8081/socket');
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = (debug: any) => {
    };

    const that = this;
    this.stompClient.connect({}, (connect: any) => {
      that.stompClient.subscribe(`STATUS`, (result: any) => {
        const frame: any = new Object(result);
        this.isExistSocket(new Match(JSON.parse(frame.body)));
      });
    });
  }

  private isExistSocket(match: Match): void {
    if (this.matchList.length) {
      let currentIndex = 0;
      let isExist = false;
      this.matchList.forEach((item: any, index: number) => {
        if (item.id === match.id) {
          currentIndex = index;
          isExist = true;
        }
      });
      if (isExist) {
        this.matchList[currentIndex] = match;
      } else {
        this.matchList.push(match);
      }
    } else {
      this.matchList.push(match);
    }
  }

}

export class Team {
  id: number;
  name: string;
  locale: string;

  constructor(obj: any) {
    this.id = obj.id ? obj.id : null;
    this.name = obj.name ? obj.name : null;
    this.locale = obj.locale ? obj.locale : null;
  }
}

export enum Status {
  NOT_STARTED,
  STARTED,
  BREAK,
  FINISHED
}

export class Match {
  id: number;
  date: any;
  country: string;
  teamOne: Team;
  teamTwo: Team;
  teamOneScore: number;
  teamTwoScore: number;
  status: Status = Status.NOT_STARTED;
  time: number;

  constructor(obj: any) {
    this.id = obj.id ? obj.id : null;
    this.date = obj.date ? obj.date : null;
    this.country = obj.country ? obj.country : null;
    this.teamOne = obj.teamOne ? obj.teamOne : null;
    this.teamTwo = obj.teamTwo ? obj.teamTwo : null;
    this.teamOneScore = obj.teamOneScore ? obj.teamOneScore : 0;
    this.teamTwoScore = obj.teamTwoScore ? obj.teamTwoScore : 0;
    this.status = obj.status ? obj.status : null;
    this.time = obj.time ? obj.time : null;
  }
}
