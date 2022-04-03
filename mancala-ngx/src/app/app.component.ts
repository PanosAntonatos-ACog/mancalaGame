import { Component } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  providers:[AppService],
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mancala-ngx';
  board:any;
  pitListPlayerA:any;
  pitListPlayerB:any;
  showSecondStep:boolean=false;
  showFirstStep:boolean=true;
  constructor(
    private appService: AppService
  ){}

  initializeGame(f: NgForm) {
    console.log(f.value);
    console.log(f.valid);
    this.appService
    .initializeGame(f.value.userName)
    .subscribe(resp =>{this.board=resp; this.pitListPlayerB=resp.pits.slice(7,13).reverse(); this.pitListPlayerA=resp.pits.slice(0,6); this.showSecondStep=true; this.showFirstStep=false});
  }

  addSecondPlayer(g: NgForm) {
    console.log(g.value);
    console.log(g.valid);
    this.appService
    .addSecondPlayer(this.board.id, g.value.secondPlayerName)
    .subscribe(resp => {this.board=resp; this.showSecondStep=false; this.showFirstStep=false});
  }

  nextMove(boardId:String, pitPosition:Number) {
    this.appService
    .nextMove(boardId, pitPosition)
    .subscribe(resp =>{this.board=resp; this.pitListPlayerB=resp.pits.slice(7,13).reverse(); this.pitListPlayerA=resp.pits.slice(0,6)});
  }
}
