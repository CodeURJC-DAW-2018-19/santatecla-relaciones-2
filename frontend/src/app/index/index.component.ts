import { Component, OnInit } from '@angular/core';
import { Unit } from './unit.model';
import { UnitService } from './unit.service';

import { Router } from '@angular/router';
import { Page } from '../page.module';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
  
})

export class IndexComponent implements OnInit {
  units:Unit[];
  lastRequestedPage:Page;
  pageNumber:number;
  search:string;

  constructor(private router: Router, private service: UnitService) { }

  ngOnInit() {
    this.pageNumber = 0;
    this.getPage();
  }

  requestNextPage() {
    this.pageNumber++;
    this.getPage();
  }

  getPage() {
    this.service.getUnits(this.search,this.pageNumber).subscribe(
      page => this.addToPage(page),
      error => console.log(error)
    );
  }

  addToPage(page: Page): void {
    this.lastRequestedPage = page;
    if(this.pageNumber === 0) {
      this.units = page.content;
    } else {
      this.units = this.units.concat(page.content);
    }
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


