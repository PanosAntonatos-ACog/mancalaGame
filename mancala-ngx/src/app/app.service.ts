import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable()
export class AppService {
  baseUrl = 'http://localhost:8080/';  // URL to web api

  constructor(
    private http: HttpClient) {
  }

  //  PUT: initialize the game. Returns the created game upon success.
  initializeGame(userName:String): Observable<any> {
    let url = this.baseUrl + "game/initializeGame?playerName=" + userName
    return this.http.put<any>(url, httpOptions).pipe();
  }

  //  PUT: initialize the game. Returns the created game upon success.
  addSecondPlayer(boardId:String, secondPLayerName:String): Observable<any> {
    let url = this.baseUrl + "game/addSecondPlayer?boardId=" + boardId + "&secondPlayerName=" + secondPLayerName;
    return this.http.put<any>(url, httpOptions).pipe();
  }

  
  /* POST: do the next move */
  nextMove(boardId:String, pitPosition:Number): Observable<any> {
    let url = this.baseUrl + "game/nextMove?boardId=" + boardId + "&pitPosition=" + pitPosition;
    return this.http.post<any>(url, httpOptions).pipe();
  }

}