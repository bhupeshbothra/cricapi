import { TestBed } from '@angular/core/testing';

import { CardServiceService } from './card-service.service';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { Player } from '../Player';

describe('CardServiceService', () => {

  let cardService: CardServiceService;
  let httpTestController: HttpTestingController;

  const testUserTrack = "http://localhost:8086/favoriteservice/api/v1/favoriteservice/";

  let player = new Player();

  player = {
    pid: "123",
    fullName: "Bhupesh Bothra",
    name: "Bhupesh"
  }
  beforeEach(() => {

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CardServiceService]

    });
    cardService = TestBed.get(CardServiceService);
    httpTestController = TestBed.get(HttpTestingController);


  });

   it('#add player to favorite list response form http call', () => {
    sessionStorage.setItem("username", "test");
    const url = testUserTrack + "user/test" + "/player";
    expect(cardService).toBeTruthy();
    cardService.addPlayerToFavoriteList(player).subscribe(res => {
      console.log(res);
      expect(res.body).toBe(player);
    });
    const httpMockReq = httpTestController.expectOne(url);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toBe(url);
  });

  it('#delete from favorite list response form http call', () => {
    sessionStorage.setItem("username", "test");
    const url = testUserTrack + "user/test" + "/player";
    expect(cardService).toBeTruthy();
    cardService.deleteTrackFromFavoriteList(player).subscribe(res => {
      console.log(res);
      //  expect(res.body).toBe(player);
    });
    const httpMockReq = httpTestController.expectOne(url);
    expect(httpMockReq.request.method).toBe('DELETE');
    expect(httpMockReq.request.url).toBe(url);
  });

  it('#Get all  favorite list response form http call', () => {
    sessionStorage.setItem("username", "test");
    const url = testUserTrack + "user/test" + "/player";
    expect(cardService).toBeTruthy();
    cardService.getAllTracksforFavoriteList().subscribe(res => {
      console.log(res);
      // expect(res.body).toBe(player);
    });
    const httpMockReq = httpTestController.expectOne(url);
    expect(httpMockReq.request.method).toBe('GET');
    expect(httpMockReq.request.url).toBe(url);
  });
  it('should be created', () => {
    //const service: CardServiceService = TestBed.get(CardServiceService);
    expect(cardService).toBeTruthy();
  });
});
