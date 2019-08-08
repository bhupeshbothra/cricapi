import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Player } from './player';
import { ReturnStatus } from './status';
import { HttpOptions } from './http-options';
import { HttpClient } from '@angular/common/http';
import { PlayerList } from './playerList';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient) { }

  saveplayer(player: Player): Observable<ReturnStatus> {
    console.log(JSON.stringify(player));
    return this.http.post<ReturnStatus>("http://localhost:9898/cricapi/v1/save",
      JSON.stringify(player), HttpOptions.httpOptions);
  }

  searchplayer(player: Player): Observable<Player[]> {
    console.log(JSON.stringify(player));
    return this.http.get<Player[]>("http://localhost:9898/cricapi/v1/getAllPlayer");
  }
}
