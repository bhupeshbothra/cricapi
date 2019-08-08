import { PlayerList } from './../playerList';
import { Component, OnInit } from '@angular/core';
import { Player } from '../player';

@Component({
  selector: 'app-playerlist',
  templateUrl: './playerlist.component.html',
  styleUrls: ['./playerlist.component.css']
})
export class PlayerlistComponent implements OnInit {

  players: Player[];
  constructor(private playerlist: PlayerList) { }

  ngOnInit() {
    if (undefined !== this.playerlist.player) {

      this.players = this.playerlist.player;

    }
    else {
      console.log('test');
    }
  }

}
