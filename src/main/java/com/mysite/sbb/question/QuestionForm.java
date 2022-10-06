package com.mysite.sbb.question;

<<<<<<< HEAD

=======
>>>>>>> 7804ab2f84287e79713fdd60e336a99d5fc3ddef
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class QuestionForm {

<<<<<<< HEAD

    @Size(max=200)
    @NotEmpty(message="제목은 필수항목입니다.")
    private String subject;

    @NotEmpty(message="내용은 필수항목입니다.")
=======
    @Size(max = 200)
    @NotEmpty(message = "제목은 필수항목입니다.")
    private String subject;

    @NotEmpty(message = "내용은 필수항목입니다.")
>>>>>>> 7804ab2f84287e79713fdd60e336a99d5fc3ddef
    private String content;
}
