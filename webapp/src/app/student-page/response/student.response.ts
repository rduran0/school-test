import { StudentDTO } from "../model/student.dto"

export interface StudentResponse {
  students: StudentDTO[]
  size: number
}
