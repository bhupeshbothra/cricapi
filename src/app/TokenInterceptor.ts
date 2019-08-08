import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    constructor() { }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        request = request.clone({
            setHeaders: {
                Authorization: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCaHVwZXNoIiwiaWF0IjoxNTY1MjU4MTIxfQ.a_rYsGwouyr18g_LZrPzzn2VORkJXuTyrtGO4RJcZmU`
            }
        });
        return next.handle(request);
    }
}