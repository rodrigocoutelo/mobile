package com.example.recoin.ui.accounts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.recoin.DB.repository.AvatarRepository;
import com.example.recoin.DB.repository.BankAccountRepository;
import com.example.recoin.DB.repository.UserRepository;
import com.example.recoin.Model.Avatar;
import com.example.recoin.Model.BankAccount;
import com.example.recoin.Model.User;
import com.example.recoin.R;
import com.example.recoin.Retrofit.Service.RequestResult;
import com.example.recoin.Util.MyToast;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateAccountFragment extends Fragment {
    private CircleImageView vAvatar;
    private Uri imageUri;
    private ImageView vGaleria;
    private ImageView vRotate;
    private ImageView vPhoto;
    private File photo;
    private ImageView vAvatarHeader;
    private Button vBTcreate;
    private int degree = -90;
    private MyToast toast;
    private String URL_SITE = "http://vps23684.publiccloud.com.br:3333/";
    private UserRepository userRepo;
    private AvatarRepository avatarRepo;
    private BankAccountRepository accountRepo;
    private boolean userSucess = false;
    private boolean avatarSucess = false;
    User newUser = new User();
    Avatar newAvatar = new Avatar();



    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // accountsViewModel = new ViewModelProvider(this).get(AccountsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_create, container, false);
        toast = new MyToast(root.getContext());
        vAvatar = root.findViewById(R.id.avatar);
        vAvatar.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            { takePhoto();

            }
        });

        vPhoto = root.findViewById(R.id.takePhoto);
        vPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();

            }
        });

        vGaleria = root.findViewById(R.id.pickImage);
        vRotate = root.findViewById(R.id.rotateButton);
        vAvatarHeader = root.findViewById(R.id.avatarHeader);

        //buscar o path salvo no banco e buscar o URI para passar para o Picasso
        File avatarsDir = new File(getActivity().getApplicationContext().getDataDir()+"/files/");
        File[] files = avatarsDir.listFiles();
        Uri avatarUri = FileProvider.getUriForFile(getActivity(), "com.example.recoin.fileprovider", files[files.length-1]);
        Log.e("FileOnOpen", files[files.length-1].getPath() );
        Log.e("UriOnOpen", avatarUri.toString() );
        Picasso.get().load(avatarUri).resize(0, 100).into(vAvatar);
        Picasso.get().load(avatarUri).fit().centerCrop().into(vAvatarHeader);
        vAvatarHeader.setAlpha(0.35f);
        vGaleria.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                pickPhoto();

            }
        });

        vBTcreate = root.findViewById(R.id.btCreate);
        vBTcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAndSendForm(root);
            }
        });

        return root;
    }


    public void validateAndSendForm(View view){
        TextInputLayout inputName;
        TextInputLayout inputEmail;
        TextInputLayout inputCPF;
        TextInputLayout inputPassword;
        TextInputLayout inputFone;
        TextInputLayout inputInicialAmmount;
        boolean bValidation = true;

        inputName = view.findViewById(R.id.text_input_name);
        inputEmail = view.findViewById(R.id.text_input_email);
        inputCPF = view.findViewById(R.id.text_input_cpf);
        inputPassword = view.findViewById(R.id.text_input_password);
        inputFone = view.findViewById(R.id.text_input_fone);
        inputInicialAmmount = view.findViewById(R.id.text_input_ammount);

        String name = inputName.getEditText().getText().toString().trim();
        String email = inputEmail.getEditText().getText().toString().trim();
        String cpf = inputCPF.getEditText().getText().toString().trim();
        String password = inputPassword.getEditText().getText().toString().trim();
        String fone = inputFone.getEditText().getText().toString().trim();
        String amount = inputInicialAmmount.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            inputName.setError("Este campo é obrigatório");
            bValidation = false;
        }
        if (email.isEmpty()) {
            inputEmail.setError("Este campo é obrigatório");
            bValidation = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("Email inválido");
            bValidation = false;
        }
        if (cpf.isEmpty()) {
            inputCPF.setError("Este campo é obrigatório");
            bValidation = false;
        }
        if (password.isEmpty()) {
            inputPassword.setError("Este campo é obrigatório");
            bValidation = false;
        }

        if (bValidation) {
            newUser.setName(name);
            newUser.setCpf(cpf);
            newUser.setEmail(email);
            newUser.setPhone(fone);
            newUser.setPws(password);
            newAvatar.setUrl(this.URL_SITE + photo.getName());
            newAvatar.setContentType("image/jpeg");


            try {
                userRepo = new UserRepository(view.getContext());
                Log.d("USERREPO", userRepo.toString());



                Map<String, RequestBody> map = new HashMap<>();
                map.put("cpf", toRequestBody(newUser.getCpf()));
                map.put("email", toRequestBody(newUser.getEmail()));
                map.put("telefone", toRequestBody(newUser.getPhone()));
                map.put("pws", toRequestBody(newUser.getPws()));
                map.put("name", toRequestBody(newUser.getName()));
                //TODO - garantir que sempre tenha um file, se já existir o avatar passar  e não for alterado enviar a mesma imagem com o mesmo nome
                //se não existir a imagem, ter sempre uma imagem generica para enviar com o mesmo nome.
                //outra possibilidade e checar se a photo existe se sim enviar por um endpoint senão envia por outro

                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), photo);
                MultipartBody.Part image = MultipartBody.Part.createFormData("avatar", newAvatar.getUrl(), requestFile);

                userRepo.createUser(image, map, Double.valueOf(amount), new RequestResult() {
                    @Override
                    public <T> void returnSuccess(T requestResult) {
                       User returnUser = (User) requestResult;
                        toast.msgL("Usuário criado: " + returnUser.toString());
                        Log.d("USUARIO_CRIADO", newUser.toString());
                    }
                    @Override
                    public void returnError(String message) {
                        toast.msgL("Erro ao criar Usuário \n" + message);
                    }
                });
//                if (userSucess) {
//                   try {
//                       newAvatar.setUrl(this.URL_SITE + photo.getName());
//                       newAvatar.setContentType("image/jpeg");
//                       RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), photo);
//                       MultipartBody.Part image = MultipartBody.Part.createFormData("avatar", newAvatar.getUrl(), requestFile);
//                       avatarRepo = new AvatarRepository(view.getContext());
//                       avatarRepo.uploadAvatar(newAvatar.getUserID(), image, new RequestResult() {
//                           @Override
//                           public <T> void returnSuccess(T requestResult) {
//                               Avatar returnAvatar = (Avatar) requestResult;
//                               avatarSucess = true;
//                               newUser.setAvatar(returnAvatar);
//                           }
//                           @Override
//                           public void returnError(String message) {
//                               toast.msgL("Erro ao fazer o upload do avatar \n" + message);
//                               Log.e("UPLOAD_AVATAR", message);
//                           }
//                       });
//                   } catch (Exception e) {
//                       Log.d("CREATE_AVATAR", e.getMessage());
//                   }
//                        if (avatarSucess) {
//                            try {
//                                userRepo.updateUser(newUser, new RequestResult() {
//                                    @Override
//                                    public <T> void returnSuccess(T requestResult) {
//                                        User returnUser = (User) requestResult;
//                                        toast.msgL("Usuário cadastrado com sucesso!");
//                                    }
//                                    @Override
//                                    public void returnError(String message) {
//                                        toast.msgL("Erro ao criar Usuário: \n " + message );
//                                        Log.e("UPDATE_USUARIO", message);
//                                    }
//                                });
//                            }catch (Exception e ) {
//                                Log.d("UPDATE_USER", e.getMessage());
//                            }
//                        }
//                }
            } catch (Exception e) {
                Log.d("CREATE_USER", e.getMessage());
            }
        }
//        if (userSucess && avatarSucess) {
//            try {
//                BankAccount account = new BankAccount();
//                account.setUser(newUser);
//                account.setAccount_balance(Double.valueOf(amount));
//                account.setStatus(1);
//                accountRepo = new BankAccountRepository(view.getContext());
//                accountRepo.crateAccount(account, new RequestResult() {
//                    @Override
//                    public <T> void returnSuccess(T requestResult) {
//                        BankAccount returnAccount = (BankAccount) requestResult;
//                        toast.msgL("Conta aberta com sucesso!");
//                    }
//
//                    @Override
//                    public void returnError(String message) {
//                        toast.msgL("Erro ao criar Conta \n" + message );
//                        Log.d("ERRO_BACKEND_CONTA", message);
//                    }
//                });
//
//            } catch (Exception e) {
//                Log.d("CREATE_ACCOUNT", e.getMessage());
//            }
//
//        }

    }

    public static RequestBody toRequestBody (String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body ;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        photo = new File(getActivity().getApplicationContext().getDataDir()+"/files/",  ts+"_avatar.jpg");
        imageUri = FileProvider.getUriForFile(getActivity(), "com.example.recoin.fileprovider", photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                imageUri);


        CreateAccountFragment.this.startActivityForResult(intent, 1888);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void pickPhoto() {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        photo = new File(getActivity().getApplicationContext().getDataDir()+"/files/",  ts+"_avatar.jpg");
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        CreateAccountFragment.this.startActivityForResult(Intent.createChooser(intent,"avatar"), 1999);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri photoTaked = imageUri;

        switch (requestCode) {
            case 1888:
                if (resultCode == Activity.RESULT_OK) {
                    vRotate.setVisibility(View.VISIBLE);
                    this.rotate(photoTaked);

                    vRotate.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(View view) {
                            rotate(photoTaked);
                        }
                    });
                }
                break;
            case 1999:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = data.getData();
                    vRotate.setVisibility(View.VISIBLE);
                    this.rotate(selectedImage);
                    vRotate.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(View view) {
                            rotate(selectedImage);
                        }
                    });

                }

        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void rotate(Uri selectedImage)  {
        degree = degree + 90;
        if (degree >= 360) {
            degree = 0;
        }
//        try {
//            boolean result = Files.deleteIfExists(photo.toPath());
//        } catch (IOException e) {
//           Log.e("Delete", e.getMessage());
//        }

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        ContentResolver cr = getActivity().getContentResolver();
        InputStream input = null;
        try {
            input = cr.openInputStream(selectedImage);
            Bitmap takenImage = BitmapFactory.decodeStream(input);
            Matrix mMatrix = new Matrix();
            mMatrix.setRotate(degree);
            takenImage = Bitmap.createBitmap(takenImage, 0, 0, takenImage.getWidth(),
                    takenImage.getHeight(), mMatrix, false);
            FileOutputStream fos = new FileOutputStream(photo);
            takenImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();


            //Uri avatarUri = FileProvider.getUriForFile(getActivity(), "com.example.recoin.fileprovider", photo);
            //Picasso.get().load(avatarUri).resize(0, 100).into(vAvatar);
            vAvatar.setImageBitmap(takenImage);
            vAvatarHeader.setImageBitmap(takenImage);
            vAvatarHeader.setAlpha(0.35f);
            //   vAvatar.setImageBitmap(takenImage);
            if (input != null) {
                input.close();
            }
        } catch (Exception e) {
            Log.e("Camera", e.toString());
            Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_SHORT)
                    .show();
        }
    }


}
