Text to Image - Image Generation with OpenAI API
This is an Android app that uses the OpenAI API to generate images based on user prompts. This app allows users to select modes, 
input text prompts, and generate images in various sizes. It also includes user authentication via Google Sign-In and leverages the Glide library to display images.

- Features:
- Google Sign-In Integration: Users can log in using their Google accounts.
- Image Generation: The app integrates with OpenAI’s image generation API to create images based on user-provided prompts.
- Mode Selector: Select different modes from a bottom sheet menu.
- Spinner for Image Sizes: Users can choose the image size (256x256, 512x512, 1024x1024) from a dropdown spinner.
- Glide Integration: The app uses the Glide library to load and display generated images.
- Progress Indicator: Displays a loading animation while the image is being generated.

- Dependencies
- Glide: For loading and displaying images.
- OkHttp: For handling HTTP requests to the OpenAI API.
- Google Sign-In: For user authentication.

- Usage
- Login: Click the login button to authenticate using Google Sign-In.
- Select Mode: Click the mode selector button to choose from different modes.
- Enter Prompt: Enter the text prompt you want to use to generate an image.
- Select Image Size: Use the spinner to choose the desired size of the generated image.
- Generate Image: Click the "Generate" button to send the prompt to the OpenAI API and display the resulting image.
