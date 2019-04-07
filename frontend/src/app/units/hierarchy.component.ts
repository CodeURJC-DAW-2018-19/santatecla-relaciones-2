import { Component, OnInit } from '@angular/core';
import { DialogUmlComponent } from '../dialogs/dialog-uml.component';
import { MatDialog } from '@angular/material';
import { ParamMap, ActivatedRoute } from '@angular/router';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-hierarchy',
  templateUrl: './hierarchy.component.html',
  styleUrls: ['./hierarchy.component.css']
})

export class HierarchyComponent implements OnInit {
  type?: string;
  unitName?: string;
  title?: string;

  ngOnInit() {
    if (this.loginService.isLogged) {
      this.activeRoute.paramMap.subscribe((params: ParamMap) => {
        this.unitName = params.get('name')
      });
    }
  }

  constructor(public dialog: MatDialog, private activeRoute: ActivatedRoute, private loginService: LoginService) { }

  openDialog(unitName: string, type: string): void {
    this.dialog.open(DialogUmlComponent, { data: { imgUrl: 'https://localhost:8080/api/unit/' + unitName + "/image/" + type }, });
  }
}