import { Component, ViewChild, OnInit, Inject } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { RelationComponent } from './relation.component';
import { HierarchyComponent } from './hierarchy.component';
import { MAT_DIALOG_DATA } from '@angular/material';
import { AppComponent } from '../app.component';
import { UnitService } from '../index/unit.service';
import { Unit } from '../index/unit.model';

@Component({
    selector: 'app-unit',
    templateUrl: './unit.component.html',
    styleUrls: ['./unit.component.css']
  })
  export class UnitComponent implements OnInit{
    
    @ViewChild('parentsComp') private parents: RelationComponent;
    @ViewChild('childrenComp') private children: RelationComponent;
    @ViewChild('compositesComp') private composites: RelationComponent;
    @ViewChild('partsComp') private parts: RelationComponent;
    @ViewChild('associatedToComp') private associatedTo: RelationComponent;
    @ViewChild('associatedByComp') private associatedBy: RelationComponent;
    @ViewChild('usesComp') private uses: RelationComponent;
    @ViewChild('usedByComp') private usedBy: RelationComponent;

    @ViewChild('compComposition') private compComposition: HierarchyComponent;
    @ViewChild('compClassification') private compClassification: HierarchyComponent;
    @ViewChild('compContext') private compContext: HierarchyComponent;

      unitName:string;
      units: Unit[];

      constructor(private router:Router,private activeRoute:ActivatedRoute, private appComponent: AppComponent, private unitService: UnitService){
        this.unitName = this.activeRoute.snapshot.params.name;
      }

      ngOnInit(){
        this.activeRoute.paramMap.subscribe((params: ParamMap) => {
          this.getUnits();
          
          this.unitName = params.get('name');
          this.configRelation(this.parents,"PADRES","inheritance","parents");
          this.configRelation(this.children,"HIJAS","inheritance","children");
          this.configRelation(this.composites,"COMPUESTOS","composition","composites");
          this.configRelation(this.parts,"PARTES","composition","parts");
          this.configRelation(this.associatedTo,"ASOCIADO A","association","associatedTo");
          this.configRelation(this.associatedBy,"ASOCIADOS A","association","associatedBy");
          this.configRelation(this.uses,"USA","use","uses");
          this.configRelation(this.usedBy,"USAN","use","usedBy");

          this.configUml(this.compContext,"context", "CONTEXTO");
          this.configUml(this.compClassification,"classification", "JERARQUÍA DE CLASIFICACIÓN");
          this.configUml(this.compComposition,"composition", "JERARQUÍA DE COMPOSICIÓN");

          this.appComponent.addTab(this.unitName);
        });

      }

      configRelation(rel:RelationComponent,type:string,typeLinkGlobal:string,typeLinkConcrete:string){
          rel.typeTitle=type;
          rel.typeRelLinksConcrete=typeLinkConcrete;
          rel.typeRelLinksGlobal=typeLinkGlobal;

      }

      configUml(comp:HierarchyComponent,type:string,title:string){
        comp.type=type;
        comp.title=title;
        comp.unitName=this.unitName;
      }

      getUnits(){
        this.unitService.getAllUnits().subscribe(
          page => {
            this.units = page.content;
            this.parents.units=this.units;
            this.children.units=this.units;
            this.associatedBy.units=this.units;
            this.associatedTo.units=this.units;
            this.uses.units=this.units;
            this.usedBy.units=this.units;
            this.composites.units=this.units;
            this.parts.units=this.units;
          },
          error => console.log(error)
        );
      }
  }