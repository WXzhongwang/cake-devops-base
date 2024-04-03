export function isImageFileType(fileName: any) {
    const imageExtensions = ['jpg', 'jpeg', 'png', 'gif', 'bmp', 'webp'];

    const fileExtension = fileName.toLowerCase().split('.').pop();

    return imageExtensions.includes(fileExtension);
}