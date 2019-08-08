import { PlayerComponent } from './player/player.component';
import { LoginComponent } from './login/login/login.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlayerlistComponent } from './playerlist/playerlist.component';

const routes: Routes = [

  {
    path: "",
    component: LoginComponent
  },
  {
    path: "PlayerSearch",
    component: PlayerComponent
  },
  {
    path: "PlayerList",
    component: PlayerlistComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
