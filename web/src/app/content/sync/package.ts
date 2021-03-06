export interface Package {
  name: string;
  uri: string;
  seq: number;
  progress: number;
  progressShow: boolean;
  buttonLabel: string;
  buttonDisabled: boolean;
}