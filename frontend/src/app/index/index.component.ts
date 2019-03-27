import { Component, OnInit } from '@angular/core';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
  
})

export class IndexComponent  {
  /*
  implements OnInit
  _asyncService: any;
  _loadingService: any;

  constructor() { }
  
  data: any[] = [];
  page: number = 0;

  ngOnInit(): void {
    this.fetch();
  }

  fetchMore(): void {
    this.page++;
    this.fetch();
  }

  private fetch(): void {
    this._loadingService.register('loading');
    this._asyncService.get(this.page)
      .pipe(
        finalize(() => this._loadingService.resolve('loading')),
      )
      .subscribe((results: any[]) => {
        this.data = this.data.concat(results);
      });
  }*/

}
