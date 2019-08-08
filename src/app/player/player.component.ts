import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { PlayerService } from './../player.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Player } from '../player';
import { ReturnStatus } from '../status';
import { PlayerList } from '../playerList';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {

  formPlayerGroup: FormGroup;
  player: Player = new Player();
  constructor(private playerService: PlayerService, private playerList: PlayerList, private router: Router) { }

  ngOnInit() {

    this.formPlayerGroup = new FormGroup({
      name: new FormControl(this.player.name, [Validators.required, Validators.minLength(3)]),
      email: new FormControl(this.player.email, [Validators.required]),
      score: new FormControl(this.player.score, [Validators.required]),
      playerId: new FormControl(this.player.cricplayerid, [Validators.required])

    });
  }

  get name() {
    return this.formPlayerGroup.get('name');
  }

  seachPlayers(value: any) {
    alert("search player");
    this.player.cricplayerid = value.playerId;
    this.player.email = value.email;
    this.player.name = value.name;
    this.player.score = value.score;

    console.log(value);

    this.playerService.searchplayer(this.player)
      .subscribe((response: Player[]) => {
        this.playerList.player = response;
        console.log(this.playerList.player);
        this.router.navigate(['/PlayerList']);
      });

  }
  saveplayer(value: any): void {
    if (this.formPlayerGroup.invalid) {
      return;
    }
    alert("savePlayer");
    this.player.cricplayerid = value.playerId;
    this.player.email = value.email;
    this.player.name = value.name;
    this.player.score = value.score;

    console.log(value);

    this.playerService.saveplayer(this.player)
      .subscribe((response: ReturnStatus) => {
        console.log(response);
        if (response.message === "success") {
          console.log("Success");
          var x = document.getElementById("snackbar");
          x.className = "show";
          setTimeout(function () { x.className = x.className.replace("show", ""); }, 3000);
        }
        else {
          console.log("Failure");
        }
      });


  }



}
