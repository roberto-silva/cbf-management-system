import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  status = Status;

  private stompClient: Stomp.Client;

  private teamOne: Team = new Team({id: 1, name: 'teamOne', locale: 'rio'});
  private teamTwo: Team = new Team({id: 2, name: 'teamTwo', locale: 'rio'});

  matchList: Match[] = [
    // {
    //   id: 1,
    //   date: new Date(),
    //   country: 'rio',
    //   teamOne: this.teamOne,
    //   teamTwo: this.teamTwo,
    //   teamOneScore: 0,
    //   teamTwoScore: 0,
    //   status: Status.NOT_STARTED,
    //   time: 0
    // },
    // {
    //   id: 1,
    //   date: new Date(),
    //   country: 'rio',
    //   teamOne: this.teamOne,
    //   teamTwo: this.teamTwo,
    //   teamOneScore: 0,
    //   teamTwoScore: 0,
    //   status: Status.FINISHED,
    //   time: 0
    // },
    // {
    //   id: 1,
    //   date: new Date(),
    //   country: 'rio',
    //   teamOne: this.teamOne,
    //   teamTwo: this.teamTwo,
    //   teamOneScore: 0,
    //   teamTwoScore: 0,
    //   status: Status.STARTED,
    //   time: 0
    // },
    // {
    //   id: 1,
    //   date: new Date(),
    //   country: 'rio',
    //   teamOne: this.teamOne,
    //   teamTwo: this.teamTwo,
    //   teamOneScore: 0,
    //   teamTwoScore: 0,
    //   status: Status.BREAK,
    //   time: 0
    // }
  ];

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
    const newMatch: Match = match;
    newMatch.date = new Date(match.date);
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
        this.matchList[currentIndex] = newMatch;
      } else {
        this.matchList.push(newMatch);
      }
    } else {
      this.matchList.push(newMatch);
    }
  }

  isStatus(statusMatch: any, status: Status): boolean {
    return statusMatch === status;
  }

  getStatus(status: Status): string {
    switch (status) {
      case Status.BREAK:
        return 'Break';
      case Status.FINISHED:
        return 'Finished';
      case Status.NOT_STARTED:
        return 'Not Started';
      case Status.STARTED:
        return 'Started';
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
  NOT_STARTED = 0,
  STARTED = 1,
  BREAK = 2,
  FINISHED = 3
}

export class Match {
  id: number;
  date: any;
  country: string;
  teamOne: Team;
  teamTwo: Team;
  teamOneScore: number;
  teamTwoScore: number;
  status: Status | any;
  time: number;

  constructor(obj: any) {
    this.id = obj.id ? obj.id : null;
    this.date = obj.date ? obj.date : null;
    this.country = obj.country ? obj.country : null;
    this.teamOne = obj.teamOne ? obj.teamOne : null;
    this.teamTwo = obj.teamTwo ? obj.teamTwo : null;
    this.teamOneScore = obj.teamOneScore ? obj.teamOneScore : 0;
    this.teamTwoScore = obj.teamTwoScore ? obj.teamTwoScore : 0;
    this.status = obj.status ? obj.status : Status.NOT_STARTED;
    this.time = obj.time ? obj.time : null;
  }
}
