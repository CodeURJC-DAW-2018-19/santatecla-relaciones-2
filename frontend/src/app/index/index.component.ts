import { Component, OnInit } from '@angular/core';
import { Unit } from '../unit.model';
import { UnitService } from '../unit.service';

import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
  
})

export class IndexComponent implements OnInit {
    units:Unit[];
  
    constructor(private router: Router, private service: UnitService) { }
  
    ngOnInit() {
      this.service.getUnits(null,0).subscribe(
        units => this.units = units,
        error => console.log(error)
      );
    }

}
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


