import { HttpHeaders } from '@angular/common/http';

export const environment = {
  production: false,
  serverURL: 'http://localhost:8080',
  serverOptions: {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
  }
};
