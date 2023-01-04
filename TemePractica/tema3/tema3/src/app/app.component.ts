import { Component, OnInit } from '@angular/core';
import { Student } from './student';
import { StudentService } from './students.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [StudentService]
})
export class AppComponent implements OnInit{
  title = 'Tema3';
  students: Student[];
  totalMarks: number;
  isAscending: Boolean = true;
  placeId:string;
  _filterText:string = '';
  filteredStudents: Student[];
  getValue(val:string)
  {
    this.filterText=val;
  }


  get filterText(){
    return this._filterText;
  }

  set filterText(value: string){
    this._filterText = value;
    this.filteredStudents = this.filterStudentByName(value);
  }

  constructor(private studentService: StudentService){

  }

  ngOnInit(){
    this.students = this.studentService.students;
    this.totalMarks = this.studentService.totalMarks;
    this.filteredStudents = this.students;
  }

  onMouseMove(){}

  filterStudentByName(filterTerm: string){
    if(this.students.length === 0 || this.filterText === ''){
      return this.students;
  } else {
      return this.students.filter((student) => 
      { 
          return student.name.toLowerCase() === filterTerm.toLowerCase();
      })
  }
  }
sortUser()
{
  console.log('sorting');
  this.isAscending=!this.isAscending;
  this.students.sort((item1:any,item2:any) => this.compare(item1,item2));
}
compare(item1:any,item2:any):number{
  let compValue=0;
  compValue= item1.name.localeCompare(item2.name,{
    sensitivity: 'base'});
    console.log(compValue);
    if(!this.isAscending){
      compValue=compValue*-1;
    }
    return compValue;
  }
}

