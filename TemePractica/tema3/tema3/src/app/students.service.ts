import { Student } from "./student";

export class StudentService{
    students: Student[] = [
        {name: 'BOGDAN', course: 'MATEMATICA', grade: 10},
        {name: 'ANDREEA', course: 'GEOMETRIE', grade: 8},
        {name: 'ANDRADA', course: 'POO', grade: 9},
        {name: 'SEBI', course: 'LFC', grade: 5},
        {name: 'BOGDAN', course: 'SISTEME OPERARE', grade: 4},
        {name: 'IOAN', course: 'SPORT', grade: 7}
    ];

    totalMarks: number = 600;
}